/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.api.helpers.common

import uk.gov.hmrc.mongo.CurrentTimestampSupport
import uk.gov.hmrc.mongo.cache.{CacheIdType, DataKey, MongoCacheRepository}
import uk.gov.hmrc.mongo.test.MongoSupport
import uk.gov.hmrc.test.api.models.otp.OtpData

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

class OtpHelper extends MongoSupport {

  override def databaseName: String = "cip-phone-number-verification"

  val repository = new MongoCacheRepository(
    mongoComponent = mongoComponent,
    collectionName = "cip-phone-number-verification",
    ttl = 1.minute,
    timestampSupport = new CurrentTimestampSupport(),
    cacheIdType = CacheIdType.SimpleCacheId
  )

  def getOtpForPhoneNumber(phoneNumber: String): Future[Option[OtpData]] = {
    repository.get[OtpData](phoneNumber)(DataKey("cip-phone-number-verification"))
  }
}